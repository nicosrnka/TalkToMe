using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using TalkToMeApi.Models;

namespace TalkToMeApi.Controllers
{

    [Route("/api/[controller]")]
    [ApiController]
    public class CaregiverController : ControllerBase
    {
        private const string CONNSTRING = "server=localhost\\SQLEXPRESS;database=talktome; User Id=talktome;Password=talktome123.";

        public CaregiverController()
        {

        }

        [HttpGet("getCaregivers")]
        public async Task<IActionResult> GetCaregivers(string email)
        {
            try
            {
                using var db = new TalkToMeContext(CONNSTRING);
                var caregivers = await db.People.Where(person => person.Email == email).Select(p => p.Caregivers).ToListAsync();
                return Ok(caregivers);
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("deleteCaregiver")]
        public async Task<IActionResult> DeleteCaregiver(string firstName, string lastName, string phoneNumber, string email)
        {
            try
            {
                using var db = new TalkToMeContext(CONNSTRING);

                //var toDelete = await db.Caregivers.
                return Ok();
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }

        [HttpPost("addCaregiver")]
        public async Task<IActionResult> AddCaregiver(string email)
        {
            try
            {
                using var db = new TalkToMeContext(CONNSTRING);
               // var caregivers = await db.People.Where(person => person.Email == email).Select(p => p.Caregivers).ToListAsync();
                return Ok();
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }

        [HttpPut("editCaregiver")]
        public async Task<IActionResult> EditCaregiver(string email)
        {
            try
            {
                using var db = new TalkToMeContext(CONNSTRING);
                // var caregivers = await db.People.Where(person => person.Email == email).Select(p => p.Caregivers).ToListAsync();
                return Ok();
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }
    }
}