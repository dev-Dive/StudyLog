import { notifySuccess } from '@/components/Toastify/success'
import { Api } from '../instance'
import { notifyError } from '@/components/Toastify/error'

export const sendEmailApi = async (mail: string) => {
  try {
    const response = await Api.post('/auth/mail/send', { mail })

    if (response.status === 200) {
      notifySuccess('인증 이메일을 전송했습니다.')
    } else {
      notifyError('이메일 전송 실패.')
    }
  } catch (error) {
    notifyError('서버에 문제가 발생했습니다.')
  }
}
