// vue.config.js
module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://25b8c82775.wicp.vip:42529/', //对应自己的接口
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}