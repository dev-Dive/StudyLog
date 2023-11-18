import { Avatar, Typography, AvatarGroup, Button } from '@mui/material'
import Image from 'next/image'

interface PostData {
  title: string
  subtitle: string
  content: string
  date: string
  authors: Author[]
  thumbnailImageUrl: string
  study: {
    id: number
    name: string
    profileImageUrl: string
    description: string
  }
}

interface Author {
  id: number
  name: string
}

export default function Content({
  title,
  subtitle,
  content,
  date,
  authors,
  thumbnailImageUrl,
  study,
}: PostData) {
  return (
    <div>
      <div>
        <Typography variant="h4" component="h4">
          {title}
        </Typography>
        <Typography variant="subtitle1" component="p">
          {subtitle}
        </Typography>
        <p>
          by {authors.map((author) => author.name).join(', ')} from {study.name}
        </p>
      </div>
      {/* 
      <Image
        src={thumbnailImageUrl}
        width={250}
        height={250}
        alt="스터디 사진"
      /> */}
      <main>{content}</main>
      <div>
        {/* <Avatar
          alt={study.name}
          src={study.profileImageUrl}
          sx={{ width: 56, height: 56 }}
        /> */}
        <p>{study.name}</p>
        <p>{study.description}</p>
      </div>
    </div>
  )
}
