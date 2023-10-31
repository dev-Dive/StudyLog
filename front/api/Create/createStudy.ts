import { Api } from '../instance'
import { notifySuccess } from '@/components/Toastify/success'
import { notifyError } from '@/components/Toastify/error'

type StudyProps = {
  name: string
  description: string
}

export const createStudy = async (study: StudyProps, toMain: () => void) => {
  try {
    const response = await Api.post('/studies', { study })

    if (response.status === 200) {
      toMain()
    } else {
      notifyError('스터디 생성에 실패했습니다.')
    }
  } catch (error: any) {
    if (error.response) {
      switch (error.response.status) {
        case 401:
          notifyError('로그인이 필요합니다.')
          toMain()
          break
        case 403:
          notifyError('스터디 생성 권한이 없습니다.')
          toMain()
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
