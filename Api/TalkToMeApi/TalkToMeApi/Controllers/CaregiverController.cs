using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using TalkToMeApi.Models;

namespace TalkToMeApi.Controllers
{
    //Controller für die Caregiver

    [Route("/api/[controller]")]
    [ApiController]
    public class CaregiverController : ControllerBase
    {
        //CONNSTRING kann schöner gelöst werden. Das ist der Pfad zur Datenbank. Siehe: localhost\\SQLEXPRESS
        private const string CONNSTRING = "server=localhost\\SQLEXPRESS;database=talktome; User Id=talktome;Password=talktome123.";

        public CaregiverController()
        {

        }

        //Liefert alle Caregiver in einer Liste als JSON zurück.
        [HttpGet("getCaregivers")]
        public async Task<IActionResult> GetCaregivers(string email)
        {
            try
            {
                using var db = new TalkToMeContext(CONNSTRING);
                var caregivers = await db.People
                    .Where(person => person.Email == email)
                    .SelectMany(p => p.Caregivers)
                    .Select(x => new CaregiverModel { Id = x.Id, FirstName = x.FirstName, LastName = x.LastName, PhoneNumber = x.PhoneNumber})
                    .ToListAsync();
                return Ok(caregivers);
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }

        //Löscht den Caregiver über die mitgegebene Id.
        [HttpDelete("deleteCaregiver")]
        public async Task<IActionResult> DeleteCaregiver(int id)
        {
            try
            {
                using var db = new TalkToMeContext(CONNSTRING);

                var toDelete = await db.Caregivers.FirstOrDefaultAsync(x => x.Id == id);
                if(toDelete == null)
                {
                    return NotFound();
                }
                db.Caregivers.Remove(toDelete);
                await db.SaveChangesAsync();
                return Ok();
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }

        //Fügt einen Caregiver hinzu.
        [HttpPost("addCaregiver")]
        public async Task<IActionResult> AddCaregiver(CaregiverModel c)
        {
            try
            {
                using var db = new TalkToMeContext(CONNSTRING);
                var person = await db.People.FirstOrDefaultAsync(x => x.Email == c.Email);
                if (person == null)
                {
                    return NotFound();
                }
                var careGiver = new Caregiver { FirstName = c.FirstName, LastName = c.LastName, PhoneNumber = c.PhoneNumber };
                db.Caregivers.Add(careGiver);
                await db.SaveChangesAsync();
                person.Caregivers.Add(careGiver);
                await db.SaveChangesAsync();

                return Ok(new CaregiverModel { FirstName = c.FirstName, LastName = c.LastName, PhoneNumber = c.PhoneNumber, Id = careGiver.Id});
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }

        //Editiert den übergebenen Caregiver.
        [HttpPut("editCaregiver")]
        public async Task<IActionResult> EditCaregiver(CaregiverModel c)
        {
            try
            {
                using var db = new TalkToMeContext(CONNSTRING);

                var toEdit = await db.Caregivers.FirstOrDefaultAsync(x => x.Id == c.Id);
                if (toEdit == null)
                {
                    return NotFound();
                }
                toEdit.FirstName = c.FirstName;
                toEdit.LastName = c.LastName;
                toEdit.PhoneNumber = c.PhoneNumber;
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