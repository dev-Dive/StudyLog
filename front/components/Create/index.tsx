'use client'

import { TextField, Typography, Button } from '@mui/material'
import styled from '@emotion/styled'
import { useState } from 'react'
import { createStudy } from '@/api/Create/createStudy'
import { useRouter } from 'next/navigation'

export default function CreateContent() {
  const [studyName, setStudyName] = useState('')
  const [studyIntro, setStudyIntro] = useState('')
  const router = useRouter()

  const isNameEmpty = !studyName.trim()
  const isIntroEmpty = !studyIntro.trim()
  const isNameTooLong = studyName.length > 20
  const isIntroTooLong = studyIntro.length > 60

  const toMain = () => {
    router.push('/')
  }

  const handleCreateStudy = () => {
    createStudy({ name: studyName, description: studyIntro }, toMain)
  }

  return (
    <S.Wrapper>
      <Typography
        gutterBottom
        variant="h4"
        component="div"
        sx={{ marginBottom: 10 }}
      >
        스터디를 생성해주세요
      </Typography>
      <TextField
        id="study-name"
        label="스터디 이름을 작성해주세요"
        helperText="20자 이내로 작성해주세요"
        variant="standard"
        error={isNameTooLong}
        sx={{ width: 700 }}
        value={studyName}
        onChange={(e) => setStudyName(e.target.value)}
      />
      <TextField
        id="study-intro"
        label="스터디 소개글을 작성해주세요"
        helperText="60자 이내로 작성해주세요"
        variant="standard"
        error={isIntroTooLong}
        sx={{ width: 700 }}
        value={studyIntro}
        onChange={(e) => setStudyIntro(e.target.value)}
      />
      <Button
        variant="contained"
        color="primary"
        sx={{ width: 500, height: 50, marginTop: 10 }}
        onClick={handleCreateStudy}
        disabled={
          isNameTooLong || isIntroTooLong || isNameEmpty || isIntroEmpty
        }
      >
        스터디 생성
      </Button>
    </S.Wrapper>
  )
}

const S = {
  Wrapper: styled.div`
    display: flex;
    flex-direction: column;
    text-align: center;
    align-items: center;
    gap: 50px;
    width: 50%;
  `,
}
