'use client'

import WriteContainer from '@/components/Write/WriteContainer'
import { RecoilRoot } from 'recoil'

export default function Write() {
  return (
    <RecoilRoot>
      <WriteContainer />
    </RecoilRoot>
  )
}
