import styled from '@emotion/styled'
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

  padding: 3.5px 8px;

  font-size: 14px;
  border-radius: 0.625rem;
  margin-right: 0.66rem;
  margin-bottom: 0.3rem;

  background-color: ${({ theme }) => theme.palette.primary.light};

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
