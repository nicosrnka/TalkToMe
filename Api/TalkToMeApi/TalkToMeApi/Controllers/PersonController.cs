using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using TalkToMeApi.Models;

namespace TalkToMeApi.Controllers
{
    [Route("/api/[controller]")]
    [ApiController]
    public class PersonController : Controller
    {
        private const string CONNSTRING = "server=localhost\\SQLEXPRESS;database=talktome; User Id=talktome;Password=talktome123.";

        public PersonController()
        {

        }

        [HttpPut("editPerson")]
        public async Task<IActionResult> EditPerson(PersonModel person)
        {
            try
            {
                using var db = new TalkToMeContext(CONNSTRING);

                var toEdit = await db.People.FirstOrDefaultAsync(x => x.Id == person.Id);

                if (toEdit == null)
                {
                    return NotFound();
                }

                toEdit.FirstName = person.FirstName;
                toEdit.LastName = person.LastName;
                toEdit.AddressOne = person.AddressOne;
                toEdit.AddressTwo = person.AddressTwo;
                toEdit.AddressThree = person.AddressThree;
                toEdit.Email = person.Email;
                toEdit.DateOfBirth = person.DateOfBirth;
                
                await db.SaveChangesAsync();
                return Ok();
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }

        [HttpGet("getPerson")]
        public async Task<IActionResult> GetPerson(int id)
        {
            try
            {
                using var db = new TalkToMeContext(CONNSTRING);

                var person = await db.People.FirstOrDefaultAsync(x => x.Id == id);

                if (person == null)
                {
                    return NotFound();
                }

                var p = new PersonModel
                {
                    FirstName = person.FirstName,
                    LastName = person.LastName,
                    AddressOne = person.AddressOne,
                    AddressTwo = person.AddressTwo,
                    AddressThree = person.AddressThree,
                    Email = person.Email,
                    DateOfBirth = person.DateOfBirth,
                };

                return Ok(p);
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }
    }
}