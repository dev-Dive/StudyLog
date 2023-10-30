import styled from '@emotion/styled'
import Image from 'next/image'

export default function Logo() {
  return <Image src="/assets/StudyLog.png" width={160} height={40} alt="Logo" />
}

const S = {
  Logo: styled.h1`
    text-align: center;
    font-family: Baloo;
    font-size: 260px;
    font-style: normal;
    font-weight: 400;
    line-height: 84.391%; /* 219.416px */
    letter-spacing: -19.5px;
  `,
}
