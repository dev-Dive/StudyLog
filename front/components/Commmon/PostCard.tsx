import Typography from '@mui/material/Typography'
import Card from '@mui/material/Card'
import CardContent from '@mui/material/CardContent'
import CardMedia from '@mui/material/CardMedia'
import CardActions from '@mui/material/CardActions'
import Avatar from '@mui/material/Avatar'
import Link from '@mui/material/Link'
import styled from '@emotion/styled'

type PostCardProps = {
  title: string
  thumbNailUrl: string
  subTitle: string
  studyName: string
}

export default function PostCard({
  title,
  thumbNailUrl,
  subTitle,
  studyName,
}: PostCardProps) {
  const hasImage = thumbNailUrl !== ''

  const truncatedTitle =
    title.length > 11 ? `${title.substring(0, 12)}...` : title

  const truncatedSubTitle =
    subTitle.length > 50 ? `${subTitle.substring(0, 50)}...` : subTitle

  return (
    <>
      <Card sx={{ width: 250, boxShadow: 3, borderRadius: 2 }}>
        <CardMedia
          component="img"
          alt="thumbnail"
          height="150"
          image={hasImage ? thumbNailUrl : '/assets/default-post-thumNail.png'}
          sx={hasImage ? {} : { backgroundColor: '#f3f3f3' }}
        />
        <CardContent sx={{ padding: '6px', height: 150 }}>
          <Typography gutterBottom variant="h6" component="div">
            {truncatedTitle}
          </Typography>
          <Typography
            variant="caption"
            color="text.secondary"
            component="div"
            style={{ whiteSpace: 'pre-line' }}
          >
            {truncatedSubTitle}
          </Typography>
        </CardContent>
        <S.StyledCardActions>
          <Avatar
            alt="사용자이름"
            src="https://i.pinimg.com/564x/a5/a2/ee/a5a2ee06b361a33be1835678413469d9.jpg"
            sx={{ width: 24, height: 24 }}
          />
          <S.StyledText gutterBottom variant="caption">
            by 작성자이름 from
          </S.StyledText>

          <Link href="#" underline="hover" color="inherit" variant="caption">
            {studyName}
          </Link>
        </S.StyledCardActions>
      </Card>
    </>
  )
}

const S = {
  StyledCardActions: styled(CardActions)`
    display: flex;
    align-items: center;
  `,
  StyledText: styled(Typography)`
    margin-bottom: 0;
  `,
}
