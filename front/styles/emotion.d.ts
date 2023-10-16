/// <reference types="@emotion/react/types/css-prop" />

import '@emotion/react'
import { theme } from './theme'

type ThemeType = typeof theme

declare module '@emotion/react' {
  export interface Theme extends ThemeType {}
}
