const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
    entry: {
        bundle: './src/main/js/components/index.js',
    }, // Entry point for React components
    output: {
        path: path.resolve(__dirname, 'src/main/resources/templates'), // Output directory for build files
        filename: '[name].js', // Output file name without content hash
        chunkFilename: '[name].js', // Chunk file names without content hash
        publicPath: '/' // Base URL for the application
    },
    module: {
        rules: [
            {
                test: /\.js$/,
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
    plugins: [
        new CleanWebpackPlugin({
            cleanOnceBeforeBuildPatterns: ['**/*'],
        }),
        new HtmlWebpackPlugin({
            template: './src/main/js/components/index.html', // Template file for HTML
            filename: 'index.html', // Output file name in the templates directory
        })
    ],
    optimization: {
        splitChunks: {
            chunks: 'all',
        },
    },
    devtool: false, // Disable source map generation
};
