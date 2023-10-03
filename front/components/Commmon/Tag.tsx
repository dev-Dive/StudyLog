import styled from '@emotion/styled'
import { color, text } from '@/styles/theme'
import { AiFillCloseCircle } from 'react-icons/ai'

type TagType = {
  text: string
  canRemove: boolean
  onRemove?: () => void
}

export default function Tag({ text, canRemove, onRemove }: TagType) {
  return (
    <StyledTag>
      <div>
        {text}
        {canRemove && <AiFillCloseCircle size={15} onClick={onRemove} />}
      </div>
    </StyledTag>
  )
}

const StyledTag = styled.span`
  display: inline-block;

  padding: 0.35rem 0.625rem;
  border-radius: 0.625rem;
  margin-right: 0.66rem;
  margin-bottom: 0.3rem;

  font-size: ${text.small['font-size']};
  font-weight: ${text.small['font-weight']};

  background-color: ${color.lightMain2};

  div {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 0.3rem;
  }

  svg {
    flex-shrink: 0;
  }
`
