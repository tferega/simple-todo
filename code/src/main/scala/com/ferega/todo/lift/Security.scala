package com.ferega.todo
package lift

import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object Security {
  val SaltSize = 24
  val HashSize = 24
  val IterationCount = 1000
  val Algorithm = "PBKDF2WithHmacSHA1"

  def createSalt = {
    val r = new SecureRandom()
    val salt = new Array[Byte](SaltSize)
    r.nextBytes(salt)
    salt
  }

  def createHash(password: String, salt: Array[Byte]) = {
    pbkdf2(password, salt, IterationCount, HashSize)
  }

  def validate(password: String, salt: Array[Byte], storedHash: Array[Byte]) = {
    val testHash = pbkdf2(password, salt, IterationCount, HashSize)
    storedHash.deep == testHash.deep
  }

  private def pbkdf2(password: String, salt: Array[Byte], iterations: Int, length: Int) = {
    val charPwd = password.toCharArray()
    val spec = new PBEKeySpec(charPwd, salt, iterations, length*8)
    val skf = SecretKeyFactory.getInstance(Algorithm)
    skf.generateSecret(spec).getEncoded
  }
}