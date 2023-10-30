import Button from '@mui/material/Button'
import useModal from '@/hooks/useModal'
import Modal from '@/components/Modal/Modal'
import { createPortal } from 'react-dom'
import { useRouter, useSearchParams } from 'next/navigation'
import { useEffect } from 'react'
import { isValidLink } from '@/api/Main/isValidLink'

export default function LoginButton() {
  const params = useSearchParams()
  const router = useRouter()
  const [modal, setModalHandler, portalElement] = useModal()

  const token = params.get('token') || ''
  const authType = params.get('type') || ''

  useEffect(() => {
    if (token !== '' && authType !== '') {
      isValidLink(token, authType)
      router.replace('/')
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
      <Button variant="outlined" sx={{ borderRadius: 30 }} onClick={openModal}>
        로그인
      </Button>
    </>
  )
}
