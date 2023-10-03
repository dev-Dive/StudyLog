import styled from '@emotion/styled'
import { color, text } from '@/styles/theme'
import { BiArrowBack } from 'react-icons/bi'
import Link from 'next/link'

export default function Footer() {
  return (
    <StyledFooter>
      <Link href="/" style={{ textDecoration: 'none' }}>
        <StyledLink>
          <BiArrowBack size={20} />
          나가기
        </StyledLink>
      </Link>

      <SubmitBtn>저장하기</SubmitBtn>
    </StyledFooter>
  )
}

const StyledFooter = styled.footer`
  box-sizing: border-box;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;

  border-top: 1px solid ${color.gray4};
  padding: 1rem;
`

const StyledLink = styled.div`
  text-decoration: none;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.2rem;
  cursor: pointer;
  color: ${color.black};
`

const SubmitBtn = styled.button`
  padding: 0.7rem 1rem;
  background-color: ${color.mainColor};
  color: ${color.white};
  border-radius: 0.625rem;

  font-size: ${text.normal['font-size']};
  font-weight: ${text.normal['font-weight']};
  line-height: ${text.normal['line-height']};
  border: none;
`
