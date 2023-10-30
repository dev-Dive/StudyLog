import NewPostButton from './NewPostButton'
import MyStudiesButton from './MyStudiesButton'
import UserProfileDropdown from './UserProfileDropdown'
import useIsLoggedIn from '@/hooks/useIsLoggedIn'
import LoginButton from './LoginButton'
import styled from '@emotion/styled'

export default function HeaderButtons() {
  const isLoggedIn = useIsLoggedIn()

  return (
    <div>
      {!isLoggedIn ? (
        <LoginButton />
      ) : (
        <S.Buttons>
          <NewPostButton />
          <MyStudiesButton />
          <UserProfileDropdown />
        </S.Buttons>
      )}
    </div>
  )
}

const S = {
  Buttons: styled.div`
    display: flex;
    justify-content: space-evenly;
    gap: 10px;
  `,
}
