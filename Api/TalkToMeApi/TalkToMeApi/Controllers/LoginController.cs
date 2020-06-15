using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using TalkToMeApi.Models;

namespace TalkToMeApi.Controllers
{
    [Route("/api/[controller]")]
    [ApiController]
    public class LoginController : ControllerBase
    {
        private const string CONNSTRING = "server=localhost\\SQLEXPRESS;database=talktome; User Id=talktome;Password=talktome123.";

        public LoginController() {
        
        }

        [HttpGet("login")]
        public async Task<IActionResult> Login(string email, string password)
        {
            try
            {
                using var db = new TalkToMeContext(CONNSTRING);
                var person = await db.People.FirstOrDefaultAsync(p => p.Email == email);
                if (person == null)
                {
                    return NotFound();
                }
                if (await db.ComparePasswordHash(password, person.Password))
                {
                    return Ok();
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }

        [HttpPost("register")]
        public async Task<IActionResult> Register(PersonModel person)
        {
            try
            {
                using var db = new TalkToMeContext(CONNSTRING);

                var p = new Person {
                    FirstName = person.FirstName,
                    LastName = person.LastName,
                    AddressOne = person.AddressOne,
                    AddressTwo = person.AddressTwo,
                    AddressThree = person.AddressThree,
                    Email = person.Email,
                    DateOfBirth = person.DateOfBirth,
                    Password = await db.CreatePasswordHash(person.Password)
                };

                db.People.Add(p);
                await db.SaveChangesAsync();
                return Ok();
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }
    }
}