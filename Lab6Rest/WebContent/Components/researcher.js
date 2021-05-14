/**
 * 
 */

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{

// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateResearcherForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidResearcherIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "ResearcherAPI",
 type : type,
 data : $("#formResearcher").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
  location.reload(true);
 onItemSaveComplete(response.responseText, status);

 }
 }); 
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidResearcherIDSave").val($(this).data("researcherid"));
 $("#researcherCode").val($(this).closest("tr").find('td:eq(0)').text());
 $("#researcherName").val($(this).closest("tr").find('td:eq(1)').text());
 $("#researcherEmail").val($(this).closest("tr").find('td:eq(2)').text());
 $("#researcherDep").val($(this).closest("tr").find('td:eq(3)').text());
});

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "ResearcherAPI",
 type : "DELETE",
 data : "researcherID=" + $(this).data("researcherid"),
 dataType : "text",
 complete : function(response, status)
 {

  location.reload(true);
 onResearcherDeleteComplete(response.responseText, status);

 }
 });
});

// CLIENT-MODEL================================================================
function validateResearcherForm()
{
// CODE
if ($("#researcherCode").val().trim() == "")
 {
 return "Insert Researcher Code.";
 }
// NAME
if ($("#researcherName").val().trim() == "")
 {
 return "Insert esearcher Name.";
 } 

// Email-------------------------------
if ($("#researcherEmail").val().trim() == "")
 {
 return "Insert researcher Email.";
 }

// DESCRIPTION------------------------
if ($("#researcherDep").val().trim() == "")
 {
 return "Insert Researcher Description.";
 }
return true;
}

function onResearcherSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divResearcherGrid").html(resultSet.data);

 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidResearcherIDSave").val("");
 $("#formResearcher")[0].reset();
}

function onResearcherDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divResearchersGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}   
 