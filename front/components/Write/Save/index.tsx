import styled from '@emotion/styled'
import Grid from '@mui/material/Grid'
import View from './View'
import Controll from './Controll'

interface SaveModalProps {
  closeModal: () => void
}

export default function SaveModal({ closeModal }: SaveModalProps) {
  return (
    <StyledContainer>
      <Grid container>
        <Grid item xs={6}>
          <View />
        </Grid>
        <Grid item xs={6}>
          <Controll closeModal={closeModal} />
        </Grid>
      </Grid>
    </StyledContainer>
  )
}

const StyledContainer = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: linear-gradient(
    288deg,
    #eceaff 1.96%,
    rgba(255, 255, 255, 0.96) 57.09%
  );
  display: flex;
  justify-content: center;
  align-items: center;
`
