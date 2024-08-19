const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
    entry: {
        react: './src/main/js/components/index.jsx',
    }, // Entry point for React components
    output: {
        filename: '[name].js',
        path: path.resolve(__dirname, 'src/main/resources/static'),
    },
    module: {
        rules: [
            {
                test: /\.jsx?$/,  // Matches both .js and .jsx files
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env', '@babel/preset-react'],
                    },
                },
            },
        ],
    },
    resolve: {
        extensions: ['.js', '.jsx'],
        modules: ['node_modules'],
    },
    plugins: [
        new CleanWebpackPlugin({
            cleanOnceBeforeBuildPatterns: ['**/*'],
        }),
        new HtmlWebpackPlugin({
            template: './src/main/js/components/ReactExample.html', // Template file for HTML
            inject: "body",
            filename: `${__dirname}/src/main/resources/templates/ReactExample.html`, // Output file name in the templates directory
            chunks: ["react"]
        })
    ],
    optimization: {
        splitChunks: {
            chunks: 'all',
        },
    },
    devtool: false, // Disable source map generation
};
