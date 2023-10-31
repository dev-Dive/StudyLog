'use client'

import React from 'react'
import styled from '@emotion/styled'
import Header from '@/components/Header'
import { ToastContainer } from 'react-toastify'

import { ThemeProvider } from '@mui/material/styles'
import theme from '@/styles/theme'

export default function CreateLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <ThemeProvider theme={theme}>
      <Header />
      <S.Layout>{children}</S.Layout>
      <ToastContainer
        position="top-right" // 알람 위치 지정
        autoClose={2000} // 자동 off 시간
        hideProgressBar={false} // 진행시간바 숨김
        closeOnClick // 클릭으로 알람 닫기
        rtl={false} // 알림 좌우 반전
        draggable // 드래그 가능
        pauseOnHover // 마우스를 올리면 알람 정지
        theme="colored"
        // limit={1} // 알람 개수 제한
      />
    </ThemeProvider>
  )
}

const S = {
  Layout: styled.section`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100vh;
  `,
}
