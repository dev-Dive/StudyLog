import axios from 'axios'

export const Api = axios.create({
  baseURL: process.env.NEXT_PUBLIC_SERVER_URL,
  withCredentials: true,
})

export const AuthApi = axios.create({
  baseURL: process.env.NEXT_PUBLIC_SERVER_URL,
  withCredentials: true,
})

AuthApi.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('accessToken')

    if (token) config.headers['Authorization'] = 'Bearer ' + token

    config.headers['Content-Type'] = 'application/json'

    return config
  },
  (error) => {
    return Promise.reject(error)
  },
)
