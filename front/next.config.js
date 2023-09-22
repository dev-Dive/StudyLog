/** @type {import('next').NextConfig} */
const nextConfig = {
  async redirects() {
    return [
      {
        source: '/login',
        destination: '/?type=login',
        permanent: false,
      },
      {
        source: '/register',
        destination: '/?type=register',
        permanent: false,
      },
    ]
  },
}

module.exports = nextConfig
