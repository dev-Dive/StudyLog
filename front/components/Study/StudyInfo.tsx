import { Avatar, Typography, AvatarGroup, Button } from '@mui/material'
import styled from '@emotion/styled'

export default function StudyInfo() {
  return (
    <S.Layout>
      <S.Profile>
        <Avatar
          alt="Remy Sharp"
          src="/assets/default-post-thumNail.png"
          sx={{ width: 180, height: 180 }}
        />
        <S.Info>
          <div>
            <Typography variant="h5" component="h5">
              스터디 이름
            </Typography>
            <Typography variant="subtitle1" component="div">
              스터디 소개
            </Typography>
          </div>

          <AvatarGroup max={4}>
            <Avatar alt="Remy Sharp" src="/assets/default-post-thumNail.png" />
            <Avatar
              alt="Travis Howard"
              src="/assets/default-post-thumNail.png"
            />
            <Avatar alt="Cindy Baker" src="/assets/default-post-thumNail.png" />
            <Avatar
              alt="Agnes Walker"
              src="/assets/default-post-thumNail.png"
            />
            <Avatar
              alt="Trevor Henderson"
              src="/assets/default-post-thumNail.png"
            />
          </AvatarGroup>
        </S.Info>
      </S.Profile>
      <Button variant="contained" color="primary" sx={{ height: 40 }}>
        팔로우
      </Button>
    </S.Layout>
  )
}

const S = {
  Layout: styled.section`
    display: flex;
    width: 1000px;
    justify-content: space-between;
    margin-top: 20px;
    border-bottom: 1px solid black;
  `,
  Profile: styled.div`
    display: flex;
  `,
  Info: styled.div`
    margin-left: 50px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  `,
}
