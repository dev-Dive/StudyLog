import { Api } from '../instance'

interface ResponseType {
  accessToken: 'string'
  refreshToken: 'string'
}

export const isValidLink = async (token: string, type: string) => {
  try {
    const response = await Api.post<ResponseType>(`/v1/auth/mail/${type}`, {
      token,
    })

    if (response.status === 200) {
      const token = response.data.accessToken
      localStorage.setItem('accessToken', token)
    }
  } catch (error) {
    console.log(error)
  }
}
