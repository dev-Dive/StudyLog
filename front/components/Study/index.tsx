'use client'

import React from 'react'
import StudyInfo from './StudyInfo'
import StudyPost from './StudyPost'
import styled from '@emotion/styled'

export default function StudyContent() {
  return (
    <S.Layout>
      <StudyInfo />
      <StudyPost />
    </S.Layout>
  )
}

const S = {
  Layout: styled.main`
    width: 100%;
  `,
}
