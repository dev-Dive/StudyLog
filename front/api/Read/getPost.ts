import { Api } from '../instance'

interface PostData {
  title: string
  subtitle: string
  content: string
  date: string
  authors: Author[]
  thumbnailImageUrl: string
  study: {
    id: number
    name: string
    profileImageUrl: string
    description: string
  }
}

interface Author {
  id: number
  name: string
}

export const getPost = async (id: number): Promise<PostData> => {
  try {
    const response = await Api.get<PostData>(`/posts/${id}`)
    if (response.status === 200 && response.data) {
      return response.data
    } else {
      throw new Error('Post not found')
    }
  } catch (error) {
    console.error(error)
    throw error
  }
}
