import styled from '@emotion/styled'

interface ContextTextAreaProps {
  content: string
  onChange: (event: React.ChangeEvent<HTMLTextAreaElement>) => void
}

export default function InputContextTextArea({
  content,
  onChange,
}: ContextTextAreaProps): JSX.Element {
  return (
    <StyledTextArea
      rows={10}
      cols={50}
      onChange={onChange}
      value={content}
      placeholder="내용을 입력해주세요"
    />
  )
}

const StyledTextArea = styled.textarea`
  width: 100%;

  border: 0;
  resize: none;
  font-size: 1.2rem;
  flex: 1;

  outline: none;

  ::-webkit-scrollbar-track {
    background-color: none;
  }
  ::-webkit-scrollbar {
    width: 5px;
    background-color: none;
  }
  ::-webkit-scrollbar-thumb {
    background-color: ${({ theme }) => theme.palette.primary.main};
    border-radius: 1rem;
  }
`
