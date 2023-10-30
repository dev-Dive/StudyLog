'use client'

import Main from '@/components/Main/Main'
import { useEffect } from 'react'
import { Global } from '@emotion/react'
import { ThemeProvider, createTheme } from '@mui/material/styles'
import theme from '@/styles/theme'

import { globalStyle } from '@/styles/global'
import Header from '@/components/Header/index'

export default function Home() {
  useEffect(() => {
    let vh = window.innerHeight * 0.01

    document.documentElement.style.setProperty('--vh', `${vh}px`)

    window.addEventListener('resize', () => {
      let vh = window.innerHeight * 0.01
      document.documentElement.style.setProperty('--vh', `${vh}px`)
    })
  }, [])

  return (
    <>
      <ThemeProvider theme={theme}>
        <Global styles={globalStyle} />
        <Header />
        <Main />
      </ThemeProvider>
    </>
  )
}
