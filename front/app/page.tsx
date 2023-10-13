'use client'

import Main from '@/components/Main/Main'
import { useEffect } from 'react'
import GlobalStyle from '@/styles/global'
import { ThemeProvider } from '@emotion/react'
import { theme } from '@/styles/theme'

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
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <Main />
    </ThemeProvider>
  )
}
