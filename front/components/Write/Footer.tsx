import styled from '@emotion/styled'
import { BiArrowBack } from 'react-icons/bi'
import Link from 'next/link'
import Button from '@mui/material/Button'
import { createPortal } from 'react-dom'
import useModal from '@/hooks/useModal'
import SaveModal from './Save'

export default function Footer() {
  const [modal, setModalHandler, portalElement] = useModal()

  const openModal = () => {
    setModalHandler()
  }

  return (
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
