namespace TalkToMeApi.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class Init : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Caregivers",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        FirstName = c.String(),
                        LastName = c.String(),
                        PhoneNumber = c.String(),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.People",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        FirstName = c.String(),
                        LastName = c.String(),
                        Email = c.String(),
                        DateOfBirth = c.DateTime(nullable: false),
                        AddressOne = c.String(),
                        AddressTwo = c.String(),
                        AddressThree = c.String(),
                        Password = c.Binary(),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.PersonCaregivers",
                c => new
                    {
                        Person_Id = c.Int(nullable: false),
                        Caregiver_Id = c.Int(nullable: false),
                    })
                .PrimaryKey(t => new { t.Person_Id, t.Caregiver_Id })
                .ForeignKey("dbo.People", t => t.Person_Id, cascadeDelete: true)
                .ForeignKey("dbo.Caregivers", t => t.Caregiver_Id, cascadeDelete: true)
                .Index(t => t.Person_Id)
                .Index(t => t.Caregiver_Id);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.PersonCaregivers", "Caregiver_Id", "dbo.Caregivers");
            DropForeignKey("dbo.PersonCaregivers", "Person_Id", "dbo.People");
            DropIndex("dbo.PersonCaregivers", new[] { "Caregiver_Id" });
            DropIndex("dbo.PersonCaregivers", new[] { "Person_Id" });
            DropTable("dbo.PersonCaregivers");
            DropTable("dbo.People");
            DropTable("dbo.Caregivers");
        }
    }
}
