import { Api } from '../instance'

export const getPost = async (id: number) => {
  try {
    const response = await Api.get(`/posts/${id}`)

    if (response.status === 200) {
      return response.data
    }
  } catch (error) {
    console.log(error)
  }
}
