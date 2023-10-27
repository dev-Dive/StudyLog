import { notifySuccess } from '@/components/Toastify/success'
import { notifyError } from '@/components/Toastify/error'
import { AuthApi } from '../instance'
import { AxiosError } from 'axios'

type TagItem = {
  id: number
  text: string
}

export type PostStateType = {
  content: string
  title: string
  subTitle: string
  studyId: number
  thumNailUrl: string
  tags: TagItem[]
  tagInput: string
}

export const savePost = async (postData: PostStateType) => {
  try {
    const requestBody = {
      studyId: postData.studyId,
      thumbnailUrl: postData.thumNailUrl,
      title: postData.title,
      subtitle: postData.subTitle,
      content: postData.content,
      tags: postData.tags.map((tag) => tag.text),
    }

    const response = await AuthApi.post('/posts', requestBody)

    if (response.status === 200) notifySuccess('글 저장에 성공했습니다.')
  } catch (error: unknown) {
    const axiosError = error as AxiosError

    if (axiosError && axiosError.response) {
      switch (axiosError.response.status) {
        case 401:
          notifyError('로그인이 필요합니다.')
          break
        case 403:
          notifyError('글 작성 권한이 없습니다.')
          break
        default:
          notifyError('서버에 문제가 발생했습니다.')
          break
      }
    } else {
      notifyError('서버에 문제가 발생했습니다.')
    }
  }
}
