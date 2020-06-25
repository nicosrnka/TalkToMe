using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Security.Claims;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using TalkToMeApi.Models;
using Microsoft.AspNetCore.Authorization;

namespace TalkToMeApi.Controllers
{
    //login Controller
    [Route("/api/[controller]")]
    [ApiController]
    public class LoginController : ControllerBase
    {
        //CONNSTRING kann schöner gelöst werden. Das ist der Pfad zur Datenbank. Siehe: localhost\\SQLEXPRESS
        private const string CONNSTRING = "server=localhost\\SQLEXPRESS;database=talktome; User Id=talktome;Password=talktome123.";

        public LoginController()
        {

        }
        //Login Methode Parameter string email, string password. Liefert Status Code okay, sowie die Id zurück bei erfolgreicher anmeldung.
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
                    //var claims = new List<Claim>()
                    //{
                    //new Claim(ClaimTypes.NameIdentifier, person.Id.ToString())
                    //};
                    //var authenticationOptions = new AuthenticationProperties()
                    //{
                    //    AllowRefresh = true,
                    //    IsPersistent = true,
                    //    IssuedUtc = DateTime.Now,
                    //    ExpiresUtc = DateTime.Now.AddMinutes(20000)
                    //    // RedirectUri = /dashboard
                    //};

                    //var claimsIdentity = new ClaimsIdentity(claims, CookieAuthenticationDefaults.AuthenticationScheme);

                    //var claimPrincipal = new ClaimsPrincipal(claimsIdentity);

                    //await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, claimPrincipal, authenticationOptions);
                    return Ok(person.Id);
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

        //SESSION MUSS NOCH IMPLEMENTIERT WERDEN

        //[HttpGet("logout")]
        //[Authorize]
        //[ResponseCache(Location = ResponseCacheLocation.None, NoStore = true)]
        //public async Task<IActionResult> Logout()
        //{
        //    try
        //    {
        //        await HttpContext.SignOutAsync(CookieAuthenticationDefaults.AuthenticationScheme);
        //        return Ok();
        //    }
        //    catch (Exception ex)
        //    {
        //        return StatusCode(500);
        //    }
        //}

        //Methode um eine neue Person anzulegen.
        [HttpPost("register")]
        public async Task<IActionResult> Register(PersonModel person)
        {
            try
            {
                using var db = new TalkToMeContext(CONNSTRING);

                var p = new Person
                {
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