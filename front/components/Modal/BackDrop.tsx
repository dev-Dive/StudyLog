import styled from '@emotion/styled'

type BackDropType = {
  children: React.ReactNode
  loginModalHandler: () => void
}

export default function BackDrop({
  children,
  loginModalHandler,
}: BackDropType) {
  const handleChildClick = (e: React.MouseEvent) => {
    e.stopPropagation()
  }

  return (
    <S.BackDrop onClick={loginModalHandler}>
      <div onClick={handleChildClick}>{children}</div>
    </S.BackDrop>
  )
}

const S = {
  BackDrop: styled.div`
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.4);
    display: flex;
    justify-content: center;
    align-items: center;
  `,
}
