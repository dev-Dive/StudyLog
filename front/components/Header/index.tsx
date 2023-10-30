import Logo from './Logo'
import styled from '@emotion/styled'
import HeaderButtons from './HeaderButtons'

export default function Header() {
  return (
    <S.Header>
      <Logo />
      <HeaderButtons />
    </S.Header>
  )
}

const S = {
  Header: styled.div`
    display: flex;
    justify-content: space-between;
    padding: 10px 80px;
  `,
}
