'use client'

import useModal from '@/hooks/useModal'
import Modal from './Auth/Modal'
import { createPortal } from 'react-dom'
import { useRouter, useSearchParams } from 'next/navigation'
import { useEffect, useState } from 'react'
import { isValidLink } from '@/api/Main/isValidLink'

export default function Main() {
  const params = useSearchParams()
  const router = useRouter()
  const [modal, setModalHandler, portalElement] = useModal()
  const [isUserLoggedIn, setIsUserLoggedIn] = useState(false)

  const token = params.get('token') || ''
  const authType = params.get('type') || ''

  useEffect(() => {
    if (token !== '' && authType !== '') {
      isValidLink(token, authType)
      router.replace('/')
      // setIsUserLoggedIn(true)
      // 사용자가 처음에 페이지에 들어왔을 때 localStorage에 accessToken이 있으면 유효한 토큰인지 확인하고 로그인 상태을 시켜준다.
    }
  }, [])

  const openModal = () => {
    setModalHandler()
  }

  return (
    <>
      {modal && portalElement
        ? createPortal(
            <Modal setModalHandler={setModalHandler} />,
            portalElement,
          )
        : null}
      {/* {isUserLoggedIn && <button onClick={openModal}>로그인</button>*/}
      <button onClick={openModal}>로그인</button>
    </>
  )
}
