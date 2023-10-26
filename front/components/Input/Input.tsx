import styled from '@emotion/styled'
import { useState } from 'react'

interface InputType {
  placeHolder: string
}

export default function Input({ placeHolder }: InputType) {
  const [text, setText] = useState('')

  const changeText = ({ target }: React.ChangeEvent<HTMLInputElement>) => {
    setText(target.value)
  }

  return (
    <StyledInput
      type="text"
      placeholder={placeHolder}
      onChange={changeText}
      value={text}
    />
  )
}

const StyledInput = styled.input`
  border: 1;
`
