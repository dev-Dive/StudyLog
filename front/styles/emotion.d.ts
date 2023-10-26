// /// <reference types="@emotion/react/types/css-prop" />
// import '@emotion/react'
// import { theme } from './theme'

// type ExtendTheme = typeof theme

// declare module '@emotion/react' {
//   export interface Theme extends ExtendTheme {}
// }

import '@emotion/react'
import { Theme as MuiTheme } from '@mui/material/styles'

declare module '@emotion/react' {
  export interface Theme extends MuiTheme {}
}
