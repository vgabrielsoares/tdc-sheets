import { apiClient, ApiResponse } from './api'

// Types
export interface LoginRequest {
  email: string
  password: string
}

export interface RegisterRequest {
  name: string
  email: string
  password: string
  confirmPassword: string
}

export interface LoginResponse {
  user: {
    id: string
    name: string
    email: string
    role: 'USER' | 'ADMIN'
    createdAt: string
    updatedAt: string
  }
  token: string
  refreshToken: string
}

export interface RefreshTokenRequest {
  refreshToken: string
}

export interface RefreshTokenResponse {
  token: string
  refreshToken: string
}

export interface ForgotPasswordRequest {
  email: string
}

export interface ResetPasswordRequest {
  token: string
  password: string
  confirmPassword: string
}

export interface ChangePasswordRequest {
  currentPassword: string
  newPassword: string
  confirmPassword: string
}

export interface UpdateProfileRequest {
  name?: string
  email?: string
}

class AuthService {
  private readonly baseUrl = '/auth'

  /**
   * Authenticate user with email and password
   */
  async login(credentials: LoginRequest): Promise<LoginResponse> {
    const response = await apiClient.post<LoginResponse>(
      `${this.baseUrl}/login`,
      credentials
    )
    return response.data
  }

  /**
   * Register new user account
   */
  async register(userData: RegisterRequest): Promise<LoginResponse> {
    const response = await apiClient.post<LoginResponse>(
      `${this.baseUrl}/register`,
      userData
    )
    return response.data
  }

  /**
   * Refresh authentication token
   */
  async refreshToken(refreshTokenData: RefreshTokenRequest): Promise<RefreshTokenResponse> {
    const response = await apiClient.post<RefreshTokenResponse>(
      `${this.baseUrl}/refresh-token`,
      refreshTokenData
    )
    return response.data
  }

  /**
   * Logout user (invalidate tokens)
   */
  async logout(): Promise<void> {
    await apiClient.post(`${this.baseUrl}/logout`)
  }

  /**
   * Send forgot password email
   */
  async forgotPassword(email: ForgotPasswordRequest): Promise<ApiResponse<null>> {
    return apiClient.post(`${this.baseUrl}/forgot-password`, email)
  }

  /**
   * Reset password with token
   */
  async resetPassword(data: ResetPasswordRequest): Promise<ApiResponse<null>> {
    return apiClient.post(`${this.baseUrl}/reset-password`, data)
  }

  /**
   * Change current user password
   */
  async changePassword(data: ChangePasswordRequest): Promise<ApiResponse<null>> {
    return apiClient.put(`${this.baseUrl}/change-password`, data)
  }

  /**
   * Get current user profile
   */
  async getProfile(): Promise<LoginResponse['user']> {
    const response = await apiClient.get<LoginResponse['user']>(`${this.baseUrl}/profile`)
    return response.data
  }

  /**
   * Update current user profile
   */
  async updateProfile(data: UpdateProfileRequest): Promise<LoginResponse['user']> {
    const response = await apiClient.put<LoginResponse['user']>(
      `${this.baseUrl}/profile`,
      data
    )
    return response.data
  }

  /**
   * Verify email with token
   */
  async verifyEmail(token: string): Promise<ApiResponse<null>> {
    return apiClient.post(`${this.baseUrl}/verify-email`, { token })
  }

  /**
   * Resend email verification
   */
  async resendVerification(): Promise<ApiResponse<null>> {
    return apiClient.post(`${this.baseUrl}/resend-verification`)
  }

  /**
   * Check if email is available for registration
   */
  async checkEmailAvailability(email: string): Promise<{ available: boolean }> {
    const response = await apiClient.get<{ available: boolean }>(
      `${this.baseUrl}/check-email?email=${encodeURIComponent(email)}`
    )
    return response.data
  }

  /**
   * Get user sessions (active logins)
   */
  async getSessions(): Promise<Array<{
    id: string
    deviceInfo: string
    ipAddress: string
    lastActivity: string
    current: boolean
  }>> {
    const response = await apiClient.get<Array<{
      id: string
      deviceInfo: string
      ipAddress: string
      lastActivity: string
      current: boolean
    }>>(`${this.baseUrl}/sessions`)
    return response.data
  }

  /**
   * Revoke specific session
   */
  async revokeSession(sessionId: string): Promise<ApiResponse<null>> {
    return apiClient.delete(`${this.baseUrl}/sessions/${sessionId}`)
  }

  /**
   * Revoke all other sessions (keep current)
   */
  async revokeAllOtherSessions(): Promise<ApiResponse<null>> {
    return apiClient.post(`${this.baseUrl}/revoke-all-sessions`)
  }
}

// Export singleton instance
export const authService = new AuthService()
export default authService
