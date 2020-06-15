using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Security.Cryptography;
using System.Threading.Tasks;

namespace TalkToMeApi.Models
{
    public class TalkToMeContext : DbContext
    {
        public TalkToMeContext() : base()
        {
        }

        public TalkToMeContext(string conn) : base(conn)
        {
        }

        public DbSet<Person> People { get; set; }
        public DbSet<Caregiver> Caregivers { get; set; }

        public async Task<byte[]> CreatePasswordHash(string password)
        {
            // Get salt
            byte[] salt;
            new RNGCryptoServiceProvider().GetBytes(salt = new byte[16]);

            // Hash password + salt with PBKDF2
            var pbkdf2 = new Rfc2898DeriveBytes(password, salt, 10000);

            // get hash as bytes
            byte[] hash = pbkdf2.GetBytes(20);
            // create a bytearray to store hashbytes plus salt
            byte[] hashBytes = new byte[36];
            // copy the salt in the front and the hash
            Array.Copy(salt, 0, hashBytes, 0, 16);
            Array.Copy(hash, 0, hashBytes, 16, 20);

            return hashBytes;
        }

        public async Task<bool> ComparePasswordHash(string password, byte[] hashedPassword)
        {
            byte[] salt = new byte[16];
            Array.Copy(hashedPassword, 0, salt, 0, 16);
            var pbkdf2 = new Rfc2898DeriveBytes(password, salt, 10000);
            var hash = pbkdf2.GetBytes(20);

            // Timing attack proof comparison
            int res = hash.Length ^ (hashedPassword.Length - 16);

            for (int i = 0; i < hash.Length && i < hashedPassword.Length; i++)
            {
                res |= hash[i] ^ hashedPassword[i + 16];
            }

            return res == 0;
        }
    }
}
