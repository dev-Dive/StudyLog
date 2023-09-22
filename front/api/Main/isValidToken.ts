import { Api } from '../instance'

export const isValidToken = async (token: string) => {
  try {
    const response = await Api.post('/v1/auth/mail/login', {
      token,
    })

    return response.status
  } catch (error) {
    console.log(error)
  }
}
