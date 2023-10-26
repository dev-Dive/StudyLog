import Typography from '@mui/material/Typography'
import styled from '@emotion/styled'
import PostCard from '../../Commmon/PostCard'
import { useRecoilValue } from 'recoil'
import { postState } from '@/states/postAtom'

export default function View() {
  const post = useRecoilValue(postState)

  return (
    <S.Container>
      <S.Title>
        <Typography variant="h4">카드 미리보기</Typography>
        <Typography variant="h6">
          해당 글은 메인 화면에서 이렇게 보입니다.
        </Typography>
      </S.Title>
      <PostCard
        title={post.title}
        thumNailUrl={post.thumNailUrl}
        subTitle={post.subTitle}
        studyName={post.studyName}
      />
    </S.Container>
  )
}

const S = {
  Container: styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  `,
  Title: styled.div`
    text-align: center;
    margin-bottom: 50px;
  `,
}
