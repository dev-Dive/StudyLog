import { createTheme } from '@mui/material/styles'

const theme = createTheme({
  palette: {
    common: {
      black: '#000000',
      white: '#ffffff',
    },
    primary: {
      main: '#5a6dd5',
      light: '#efedff',
      dark: '#a5a5db',
    },
  },
  typography: {
    fontFamily: 'NanumSquare',
  },
})

export default theme
