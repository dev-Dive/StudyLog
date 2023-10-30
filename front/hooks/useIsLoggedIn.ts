import { useState, useEffect } from 'react'

const useIsLoggedIn = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false)

  useEffect(() => {
    const token = localStorage.getItem('accessToken')

    if (token) {
      setIsLoggedIn(true)
    } else {
      setIsLoggedIn(false)
    }
  }, [])

  return isLoggedIn
}

export default useIsLoggedIn
