'use client'

import { AiOutlineClose } from 'react-icons/ai'
import { useState } from 'react'
import BackDrop from './BackDrop'
import { Modal, EmailBtn, ToRegister } from './Modal.styles'
import { isValidEmail } from '@/utils/validation'
import { ToastContainer } from 'react-toastify'
import { notifyError } from '@/components/Toastify/error'
import { sendEmailApi } from '@/api/Main/sendEmail'

type LoginModalProps = {
  setModalHandler: () => void
}

export default function LoginModal({ setModalHandler }: LoginModalProps) {
  const [authType, setAuthType] = useState('login')
  const [input, setInput] = useState('')

  const onChangeInput = ({ target }: React.ChangeEvent<HTMLInputElement>) => {
    setInput(target.value)
  }

  const onSendEmail = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    const isValid = isValidEmail(input)

    if (isValid) {
      sendEmailApi(input)
    } else {
      notifyError('이메일 형식으로 입력해주세요.')
    }
  }

  const onchangeAuthType = () => {
    setAuthType('register')
  }

  return (
    <>
      <BackDrop loginModalHandler={setModalHandler}>
        <Modal>
          <div>
            <AiOutlineClose size={23} onClick={setModalHandler} />
          </div>
          <div>
            <h1>{authType === 'login' ? '로그인' : '회원가입'}</h1>
            <div>
              <form onSubmit={onSendEmail}>
                <input
                  type="text"
                  placeholder="이메일을 입력하세요"
                  value={input}
                  onChange={onChangeInput}
                />
                <EmailBtn type="submit">
                  {authType === 'login' ? '로그인' : '회원가입'}
                </EmailBtn>
              </form>
            </div>
            {authType === 'login' && (
              <ToRegister>
                <p>
                  회원가입이 필요하신가요?
                  <button onClick={onchangeAuthType}>회원가입</button>
                </p>
              </ToRegister>
            )}
          </div>
        </Modal>
        <ToastContainer
          position="top-right" // 알람 위치 지정
          autoClose={3000} // 자동 off 시간
          hideProgressBar={false} // 진행시간바 숨김
          closeOnClick // 클릭으로 알람 닫기
          rtl={false} // 알림 좌우 반전
          pauseOnFocusLoss // 화면을 벗어나면 알람 정지
          draggable // 드래그 가능
          pauseOnHover // 마우스를 올리면 알람 정지
          theme="colored"
          // limit={1} // 알람 개수 제한
        />
      </BackDrop>
    </>
  )
}
