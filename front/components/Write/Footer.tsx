import styled from '@emotion/styled'
import { BiArrowBack } from 'react-icons/bi'
import Link from 'next/link'
import Button from '@mui/material/Button'
import { createPortal } from 'react-dom'
import useModal from '@/hooks/useModal'
import SaveModal from './Save'
import { useRecoilValue } from 'recoil'
import { postState } from '@/states/postAtom'
import { notifyError } from '../Toastify/error'
import { ToastContainer } from 'react-toastify'

export default function Footer() {
  const [modal, setModalHandler, portalElement] = useModal()
  const post = useRecoilValue(postState)

  const openModal = () => {
    console.log(1)
    if (post.title === '') {
      notifyError('포스트 제목을 작성해주세요')
      return
    }
    setModalHandler()
  }

  return (
    <>
      <StyledFooter>
        <Link href="/" style={{ textDecoration: 'none' }}>
          <StyledLink>
            <BiArrowBack size={20} />
            나가기
          </StyledLink>
        </Link>

        <Button variant="contained" onClick={openModal}>
          저장하기
        </Button>
        {modal && portalElement
          ? createPortal(
              <SaveModal closeModal={setModalHandler} />,
              portalElement,
            )
          : null}
      </StyledFooter>
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
    </>
  )
}

const StyledFooter = styled.footer`
  box-sizing: border-box;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;

  border-top: 1px solid ${({ theme }) => theme.palette.primary.main};
  padding: 1rem;
`

const StyledLink = styled.div`
  text-decoration: none;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.2rem;
  cursor: pointer;
  color: ${({ theme }) => theme.palette.common.black};
`

const SubmitBtn = styled.button`
  padding: 0.7rem 1rem;
  background-color: ${({ theme }) => theme.palette.primary.main};
  color: ${({ theme }) => theme.palette.common.white};
  border-radius: 0.625rem;

  border: none;
`
