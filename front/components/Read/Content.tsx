import { Avatar, Typography, AvatarGroup, Button } from '@mui/material'
import Image from 'next/image'

type Props = {
  title: 'string'
  subtitle: 'string'
  content: 'string'
  date: '2023-11-01'
  authors: [
    {
      id: 0
      name: 'string'
    },
  ]
  thumbnailImageUrl: 'string'
  study: {
    id: 0
    name: 'string'
    profile_image_url: 'string'
    description: 'string'
  }
}

export default function Content({
  title,
  subtitle,
  content,
  date,
  authors,
  thumbnailImageUrl,
  study,
}: Props) {
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

      <Image
        src={thumbnailImageUrl}
        width={250}
        height={250}
        alt="스터디 사진"
      />
      <main>{content}</main>
      <div>
        <Avatar
          alt={study.name}
          src={study.profile_image_url}
          sx={{ width: 56, height: 56 }}
        />
        <p>{study.name}</p>
        <p>{study.description}</p>
      </div>
    </div>
  )
}
