'use client'

import WriteContainer from '@/components/Write/Write'
import { RecoilRoot } from 'recoil'
import theme from '@/styles/theme'
import { ThemeProvider } from '@emotion/react'

export default function Write() {
  return (
    <ThemeProvider theme={theme}>
      <RecoilRoot>
        <WriteContainer />
      </RecoilRoot>
    </ThemeProvider>
  )
}
