'use client'

import Image from 'next/image'
import styles from './page.module.css'
import Main from '@/components/Main/Main'

export default function Home() {
  let vh = window.innerHeight * 0.01

  document.documentElement.style.setProperty('--vh', `${vh}px`)

  window.addEventListener('resize', () => {
    let vh = window.innerHeight * 0.01
    document.documentElement.style.setProperty('--vh', `${vh}px`)
  })

  return <Main />
}
