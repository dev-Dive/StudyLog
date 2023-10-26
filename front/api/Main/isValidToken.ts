import { Api } from '../instance'

export const isValidToken = async (token: string) => {
  try {
    const response = await Api.post('/auth/mail/login', {
      token,
    })

    return response.status
  } catch (error) {
    console.log(error)
  }
}
